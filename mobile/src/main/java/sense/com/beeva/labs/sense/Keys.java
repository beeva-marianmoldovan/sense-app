package sense.com.beeva.labs.sense;

import java.util.Map;

/**
 * Created by marianclaudiu on 24/08/15.
 */
public interface Keys {
    public final static String UUID = "ddf86afc-6404-11e4-b116-123b93f75cba";
    public final static String TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX3JvbGUiOiJVc2VyIiwidXVpZCI6ImRkZjg2YWZjLTY0MDQtMTFlNC1iMTE2LTEyM2I5M2Y3NWNiYSIsImlhdCI6MTQzMzI1NjY5Mn0.Dt3PXaVpxJFkFzejA-HZ4jV53CJfqUj6ym0UIo2Bgos";
    public final static String ADMIN_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX3JvbGUiOiJBZG1pbiIsInV1aWQiOiJkZGY4NmFmYy02NDA0LTExZTQtYjExNi0xMjNiOTNmNzVjYmEiLCJpYXQiOjE0MzMyNTY2OTJ9.BRffs4X_ekZWE4P11vLdY8kH2KyTX3i4wLgIfGl5Aw8";

    public final static String ENDPOINT = "AYFX1339OONLE.iot.eu-west-1.amazonaws.com";
    public final static String UBIQONS_HOST = "http://54.229.131.249:8080";
    public final static String UBIQONS = "52.18.80.55";
    public final static String QUASAR_HOST = "http://52.18.80.55";

    public final static String API_CONTEXT = "/api/context";
    public final static String API_VOLUME = "/api/generic/volume";
    public final static String API_THING = "/api/thing";
    public static final String API_SENSORTAGS = "/api/sensortag";
    public static final String API_DATUM = "/api/datum";
    public static final String API_SPEAKERS = "/api/generic/speakers?limit=20";
    public static final String API_TALKS = "/api/generic/schedule?limit=200";

}
