package com.anonuser.company.enums;

public enum ResourceOp {

    READ (0),
    WRITE (1),
    EXECUTE (2),
    CONNECT(3),
    ACCEPT(4),
    START(5),
    STOP(6),
    ;

    private int id;

    public int getId() {
        return id;
    }

    ResourceOp(int id) {
        this.id = id;
    }
    public static ResourceOp getResourceOp(int id) {
        for (ResourceOp res: values()) {
            if (res.id == id) {
                return res;
            }
        }

        return null;
    }

    public static int getResourceOpId(ResourceOp resourceOp) {
        for (ResourceOp res: values()) {
            if (res == resourceOp) {
                return res.id;
            }
        }

        return -1;
    }
}
