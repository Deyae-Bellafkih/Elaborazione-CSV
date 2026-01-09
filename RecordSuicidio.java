public class RecordSuicidio {
    private String[] campi;
    private int mioValore;
    private boolean cancellato;

    public RecordSuicidio(String[] campi, int mioValore) {
        this.campi = campi;
        this.mioValore = mioValore;
        this.cancellato = false;
    }

    public String[] getCampiCompleti() {
        String[] risultato = new String[campi.length + 2];
        System.arraycopy(campi, 0, risultato, 0, campi.length);
        risultato[risultato.length - 2] = String.valueOf(mioValore);
        risultato[risultato.length - 1] = String.valueOf(cancellato);
        return risultato;
    }

    public String getChiave() {
        return campi[0];
    }

    public void modificaCampo(int indice, String valore) {
        if (indice >= 0 && indice < campi.length) {
            campi[indice] = valore;
        }
    }

    public void cancellaLogicamente() {
        cancellato = true;
    }

    public boolean isCancellato() {
        return cancellato;
    }
}
