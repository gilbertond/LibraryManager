package lib.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PrimaryKeyGenerator {
    public static String primaryKey() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyyMMddhms");
        return format.format(date);
    }
}
