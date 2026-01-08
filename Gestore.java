import java.io.*;
import java.util.Random;

public class Gestore {

    private RecordSuicidio[] records;
    private int numeroRecord;
    private String[] intestazione;
    private Random random = new Random();

    public Gestore() {
        records = new RecordSuicidio[0];
        numeroRecord = 0;
    }

    public void caricaFile(String nomeFile) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(nomeFile));

        intestazione = separa(br.readLine());
        intestazione = estendiArray(intestazione, 2);
        intestazione[intestazione.length - 2] = "miovalore";
        intestazione[intestazione.length - 1] = "cancellato";

        String riga;
        while ((riga = br.readLine()) != null) {
            String[] campi = separa(riga);
            aggiungiRecordInterno(new RecordSuicidio(campi, 10 + random.nextInt(11)));
        }
        br.close();
    }

    private void aggiungiRecordInterno(RecordSuicidio record) {
        RecordSuicidio[] nuovo = new RecordSuicidio[numeroRecord + 1];
        System.arraycopy(records, 0, nuovo, 0, numeroRecord);
        nuovo[numeroRecord] = record;
        records = nuovo;
        numeroRecord++;
    }

    public int contaCampi() {
        return intestazione.length;
    }

    public int[] lunghezzaMassimaCampi() {
        int[] max = new int[intestazione.length];
        for (int i = 0; i < numeroRecord; i++) {
            String[] campi = records[i].getCampiCompleti();
            for (int j = 0; j < campi.length; j++) {
                if (campi[j].length() > max[j]) {
                    max[j] = campi[j].length();
                }
            }
        }
        return max;
    }

    public RecordSuicidio cercaPerChiave(String chiave) {
        for (int i = 0; i < numeroRecord; i++) {
            if (records[i].getChiave().equals(chiave)) {
                return records[i];
            }
        }
        return null;
    }

    public void aggiungiRecord(String[] campi) {
        aggiungiRecordInterno(
                new RecordSuicidio(campi, 10 + random.nextInt(11))
        );
    }

    public void visualizzaTreCampi() {
        for (int i = 0; i < numeroRecord; i++) {
            String[] c = records[i].getCampiCompleti();
            System.out.println(c[0] + " | " + c[1] + " | " + c[2]);
        }
    }

    public void salvaFile(String nomeFile) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(nomeFile));

        pw.println(String.join(";", intestazione));
        for (int i = 0; i < numeroRecord; i++) {
            pw.println(String.join(";", records[i].getCampiCompleti()));
        }

        pw.close();
    }

    private String[] separa(String riga) {
        return riga.contains(";") ? riga.split(";") : riga.split(",");
    }

    private String[] estendiArray(String[] originale, int extra) {
        String[] nuovo = new String[originale.length + extra];
        System.arraycopy(originale, 0, nuovo, 0, originale.length);
        return nuovo;
    }
}