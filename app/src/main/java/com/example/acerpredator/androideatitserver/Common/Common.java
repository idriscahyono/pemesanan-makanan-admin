package com.example.acerpredator.androideatitserver.Common;

import com.example.acerpredator.androideatitserver.Model.User;

public class Common {
    public static User currentUser;

    public static final String UPDATE = "Update";
    public static final String DETELE = "Delete";

    public static final int PICK_IMAGE_REQUEST = 17;

    public static String status(String code)
    {
        if (code.equals("0"))
            return "Cooked";
        else
            return "Delivered";
    }
}
