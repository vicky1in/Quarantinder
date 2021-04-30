package com.groun24.quarantinder.Services;

import java.util.List;

import javax.transaction.Transactional;

import com.groun24.quarantinder.dao.ReportDAO;
import com.groun24.quarantinder.Modal.Report;
import com.groun24.quarantinder.Modal.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasicReportManager implements ReportManager {

    @Autowired
    ReportDAO reportDAO;

    @Transactional
    @Override
    public List<Report> get() {
        return reportDAO.get();
    }

    @Transactional
    @Override
    public Report get(int reportID) {
        return reportDAO.get(reportID);
    }

    @Transactional
    @Override
    public Report get(User reporter, User reportee) {
        return reportDAO.get(reporter, reportee);
    }

    @Transactional
    @Override
    public List<Report> get(User reportee) {
        return reportDAO.get(reportee);
    }

    @Transactional
    @Override
    public void save(Report report) {
        if (report.getReporter().equals(report.getReportee())) {
            return;
        }
        reportDAO.save(report);
    }

    @Transactional
    @Override
    public void delete(int reportID) {
        if (reportDAO.get(reportID) != null) {
            reportDAO.delete(reportID);
        }
    }
}
