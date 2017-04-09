package authSatellizer.services;

import java.util.List;

/**
 * Created by LaurentF on 07/04/2017.
 */
public interface CrudService<T> {

    List<?> listAll();

    T getById(Integer id);

    T saveOrUpdate(T domainObject);

    void delete(Integer id);
}
