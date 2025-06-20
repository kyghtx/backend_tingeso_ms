package tingeso.ev2.reports_uh.ms.entities;

import lombok.Data;

@Data
public class MonthEntity {
    private int month;
    private String month_name;

    public MonthEntity(int month, String month_name) {
        this.month = month;
        this.month_name = month_name;
    }
}
