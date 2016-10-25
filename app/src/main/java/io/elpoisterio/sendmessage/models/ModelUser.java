package io.elpoisterio.sendmessage.models;

/**
 * Created by rishabh on 21/10/16.
 */

public class ModelUser {
    private String name;
    private String thread_id;
    private String seen;
    private String body;
    private String dateReceived;
    private String userId;
    private String type;
    private String address;
    private String read;
    private String callback_number;
    private String person;
    private String time;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThread_id() {
        return thread_id;
    }

    public void setThread_id(String thread_id) {
        this.thread_id = thread_id;
    }

    public String getSeen() {
        return seen;
    }

    public void setSeen(String seen) {
        this.seen = seen;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    public String getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(String dateReceived) {
        this.dateReceived = dateReceived;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getType() {
        return type;
    }

    /**
     * 1 for received, 0 for sent
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRead() {
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }

    public String getCallback_number() {
        return callback_number;
    }

    public void setCallback_number(String callback_number) {
        this.callback_number = callback_number;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
