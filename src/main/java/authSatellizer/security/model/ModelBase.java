package authSatellizer.security.model;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Created by LaurentF on 07/04/2017.
 */
public class ModelBase implements Serializable{

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
