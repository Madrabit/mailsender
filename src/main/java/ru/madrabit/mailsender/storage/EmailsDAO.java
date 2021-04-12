package ru.madrabit.mailsender.storage;

import java.util.List;

public interface EmailsDAO {

    void readEmails();

    List<String> getEmails();
}
