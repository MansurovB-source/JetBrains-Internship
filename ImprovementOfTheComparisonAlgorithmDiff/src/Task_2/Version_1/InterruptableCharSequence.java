package Task_2.Version_1;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Behruz Mansurov
 */
public class InterruptableCharSequence implements CharSequence {
    private final CharSequence charSequence;
    private boolean status = false;

    public InterruptableCharSequence(CharSequence charSequence) {
        super();
        this.charSequence = charSequence;
    }

    @Override
    public int length() {
        return charSequence.length();
    }

    @Override
    public char charAt(int index) {
        if (Thread.currentThread().isInterrupted()) {
            throw new MatchException();
        }
        return charSequence.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return new InterruptableCharSequence(charSequence.subSequence(start, end));
    }

    public boolean isStatus() {
        return status;
    }
}
