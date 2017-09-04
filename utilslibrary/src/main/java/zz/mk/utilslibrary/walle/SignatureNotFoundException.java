package zz.mk.utilslibrary.walle;

/**
 * author: zhu on 2017/7/10 15:37
 * email: mackkilled@gmail.com
 */

public class SignatureNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public SignatureNotFoundException(final String message) {
        super(message);
    }

    public SignatureNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
}