package com.groun24.quarantinder.Modal;

public class ZoomSchedule {
    private String topic;
    private String meetingDate;
    private String meetingTime; //e.g. 12:02:00
    private int duration; // in minutes

    public void setTopic(String topic) {this.topic = topic;}
    public String getTopic() {
        return this.topic;
    }

    public int getType() {
        return 2;  // 1 for instant meeting, 2 for scheduled meeting
    }

    public String getMeetingDate() { return this.meetingDate; }

    public void setMeetingDate(String date) { this.meetingDate = date; }

    public String getMeetingTime() { return this.meetingTime; }

    public void setMeetingTime(String time) { this.meetingTime = time; }

    public String getStartTime() {
        String scheduledTime = this.meetingDate + "T" + this.meetingTime + "Z";
        //e.g. 2020-03-31T12:02:00Z
        return scheduledTime;
    }

    public void setDuration(int duration) {this.duration = duration;}

    public int getDuration() {return this.duration;}

    public String getTimezone() {return "Australia/Sydney";}
}
