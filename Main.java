import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        Gestore gestore = new Gestore();

        try {
            System.out.println("Caricamento del file CSV...");
            gestore.caricaFile("Bellafkih.csv");
            System.out.println("File caricato correttamente.");
            System.out.println("Numero di campi (inclusi miovalore e cancellato): " + gestore.contaCampi());

            System.out.println("\nVisualizzazione di tre campi:");
            gestore.visualizzaTreCampi(0, 7, 11);

            String[] nuoviCampi = {"Death rates for suicide MODIFICATO", "Deaths per 100,000", "1", "Total"};
            gestore.modificaRecord("Death rates for suicide", nuoviCampi);

            gestore.cancellaRecord("Death rates for suicide");

            String[] nuovoRecord = {"Nuovo Record", "Unità", "99", "Nome", "0", "Label", "0", "2026", "100", "All ages", "0", "0.0", "F"};
            gestore.aggiungiRecord(nuovoRecord);

            gestore.salvaFileFisso("Bellafkih_output.csv");
            System.out.println("\nFile Bellafkih_output.csv creato correttamente.");

        } catch (IOException e) {
            System.out.println("Errore nella gestione del file CSV");
        }
    }
}
