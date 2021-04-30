package com.groun24.quarantinder.Services;

import java.util.List;

import com.groun24.quarantinder.Modal.Report;
import com.groun24.quarantinder.Modal.User;

public interface ReportManager {
    
    public List<Report> get();
    
    public Report get(int reportID);

    public Report get(User reporter, User reportee);
    
    public List<Report> get(User reportee);

    public void save(Report report);
    
    public void delete(int reportID);    
}
