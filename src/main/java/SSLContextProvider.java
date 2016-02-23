import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;

/**
 * Created by jins on 2016-02-24.
 */
public class SSLContextProvider{
    /*
    *  * <li> keytool -genkey -alias keyAlias -keyalg RSA -keypass changeit -storepass changeit -keystore  mystore.jks
 * </ul>
 * <li> keytool -export -alias keyAlias -file mycert.cer -keystore mystore.jks -storepass changeit

    *
    * */
    private static SSLContext context = null;

    public static SSLContext getInstance() {
        synchronized (SSLContextProvider.class) {
            if (context != null)
                return context;

            try {
                context = SSLContext.getInstance("TLSv1");
                InputStream is = new FileInputStream(new File("C:\\SSLTest\\src\\main\\resources\\mystore.jks"));
                KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
                ks.load(is, "changeit".toCharArray());
                KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
                kmf.init(ks, "changeit".toCharArray());

                context.init(kmf.getKeyManagers(), null, null);


            } catch (Exception exp) {
                System.out.println("exp cert" + exp);
                context = null;
            }
            finally {
                return context;
            }
        }
    }


}
