package uir.ac.ma.todolist.entity;


public enum Statut {
    A_FAIRE("À faire"),
    EN_COURS("En cours"),
    TERMINE("Terminé");

    private final String label;

    Statut(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
