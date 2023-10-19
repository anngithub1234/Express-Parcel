public class Parcel {
    private int parcelId;
    private String dec;

    public Parcel(int parcelId, String dec) {
        this.parcelId = parcelId;
        this.dec = dec;
    }

    public int getParcelId() {
        return parcelId;
    }

    public String getDec() {
        return dec;
    }
}
