package be.glever.antplus.power;

/**
 * Pedal Power.
 */
public class PedalPower {
    private int percentage;
    private final boolean zeroIsLeft;

    /**
     * Constructs a new {@link PedalPower}.
     *
     * @param zeroIsLeft the zero is left
     * @param percentage the percentage
     */
    public PedalPower(boolean zeroIsLeft, int percentage) {
        this.zeroIsLeft = zeroIsLeft;
        this.percentage = percentage;
    }

    /**
     * Returns the percentage.
     *
     * @return the percentage.
     */
    public int getPercentage() {
        return percentage;
    }

    /**
     * Sets the percentage.
     *
     * @param percentage the percentage
     */
    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    /**
     * Returns whether zero is left is enabled or active.
     *
     * @return {@code true} if zero is left, {@code false} otherwise.
     */
    public boolean getZeroIsLeft() {
        return zeroIsLeft;
    }
}
