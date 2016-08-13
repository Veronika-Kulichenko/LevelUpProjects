package com.ivanovcorporation.dao;

import com.ivanovcorporation.domain.Avatar;

import java.util.List;

/**
 * Created by java on 19.03.2016.
 */
public interface AvatarDAO {

    Avatar get(Long avatarid);

    Long save(Avatar avatar);

    void delete(Avatar avatar);

    void deleteByUser(Long userid);

    List<Avatar> list();

    List<Avatar> listOfAccountsByUser(Long customerid);


}
