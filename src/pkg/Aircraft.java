package pkg;

public abstract class Aircraft {
    private String ID;
    private String type;

    public Aircraft(String ID, String type) {
        this.ID = ID;
        this.type = type;
    }

    @Override
    public String toString() {
        return ID + "|" + type;
    }

    @Override
    public boolean equals(Object obj) {
        boolean eq = false;
        if (obj instanceof Aircraft) {
            Aircraft other = (Aircraft) obj;
            eq = other.ID.equals(ID) && other.type.equals(type);
        }
        return eq;
    }
}
