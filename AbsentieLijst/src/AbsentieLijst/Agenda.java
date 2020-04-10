package AbsentieLijst;

import java.time.LocalDate;

public class Agenda {
    private LocalDate datums;


    public Agenda(LocalDate da) {
        da = datums;
    }

    public void setDatums(LocalDate datums) {
        this.datums = datums;
    }

    public LocalDate getDatums() {
        return datums;
    }

    @Override
    public String toString() {
        return "Agenda{" +
                "datums=" + datums +
                '}';
    }
}
