package test.java.com.softserve.edu.oms.data;

/**
 * Created by Xdr on 6/4/15.
 */
public class UrlRepository {
    public static enum Urls {
        LOCAL_HOST("http://localhost:8080/OMS/login.htm"),
        SSU_HOST("http://ssu-oms:8180/OMS/login.htm");
        private String field;

        private Urls(String field) {
            this.field = field;
        }

        @Override
        public String toString() {
            return this.field;
        }
    }
}
