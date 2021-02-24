package indexes.writers;

public enum IndexBuilderProgress
{
    INSTANCE;

    private double progress;

    IndexBuilderProgress() {
        progress = 0.0;
    }

    public void addProgress (double progress) {
        if (this.progress < 100)
            this.progress += progress;
    }
    public double getProgress () {
        return progress;
    }
}
