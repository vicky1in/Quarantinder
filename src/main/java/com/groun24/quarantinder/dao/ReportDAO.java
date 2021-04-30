package com.groun24.quarantinder.dao;

import java.util.List;

import com.groun24.quarantinder.Modal.Report;
import com.groun24.quarantinder.Modal.User;

import org.springframework.stereotype.Repository;

@Repository(value = "ReportDAO")
public class ReportDAO extends BaseDAO<Report> {

    public ReportDAO() {
        super(Report.class);
    }

    public List<Report> get() {
        return queryDatabase("FROM Report");  
    } 
    
    public Report get(User reporter, User reportee) {
        final String queryString = String.format("FROM Report WHERE reporter = %d AND reportee = %d", reporter.getId(), reportee.getId());
        final List<Report> list = queryDatabase(queryString);
        if (list.isEmpty()) {
            return null;
        }
        else {
            return list.get(0);
        }
    }

    public List<Report> get(User reportee) {
        final String queryString = String.format("FROM Report WHERE reportee = %d", reportee.getId());
        return queryDatabase(queryString);
    }
}
