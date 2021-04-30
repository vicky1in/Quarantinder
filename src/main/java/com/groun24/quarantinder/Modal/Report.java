package com.groun24.quarantinder.Modal;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name = "Report")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int reportID;
    
    @ManyToOne
    @JoinColumn(name = "Reporter", referencedColumnName="UserID")
    private User reporter;

    @ManyToOne
    @JoinColumn(name = "Reportee", referencedColumnName="UserID")
    private User reportee;
    
    @Column
    @NotEmpty
    private String reason;

    @Column
    @NotEmpty
    private Timestamp timestamp;

    public int getReportID() {
        return reportID;
    }

    public void setreportID(int reportID) {
        this.reportID = reportID;
    }

    public User getReporter() {
        return reporter;
    }

    public void setReporter(User reporter) {
        this.reporter = reporter;
    }

    public User getReportee() {
        return reportee;
    }

    public void setReportee(User reportee) {
        this.reportee = reportee;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
