package com.nus.iss.ejava.ca2.web;

import com.nus.iss.ejava.ca2.dao.NoteDao;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import com.nus.iss.ejava.ca2.entity.Note;
import com.nus.iss.ejava.ca2.notification.NoteNotifier;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.json.Json;
import javax.json.JsonObject;

/**
 *
 * @author mayuran
 */
@RequestScoped
@ServerEndpoint("/notes")
public class NotesWebSocketEndPoint implements Observer {

    private String category;
    private Session session;
    @Inject
    private NoteDao noteDao;

    @OnOpen
    public void onOpen(Session session) throws IOException {
        this.session = session;
        this.category = session.getRequestParameterMap().get("category").get(0);
        System.out.println("Onsession: " + session);
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("Message:" + message);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Notification received");
        Note note = (Note) arg;
        if (this.category != null && (this.category.equals("ALL")
                || this.category.equals(note.getCategory().toString()))) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy:HH:mm");

            JsonObject jsonObject = Json.createObjectBuilder()
                    .add("id", note.getNoteId())
                    .add("title", note.getTitle())
                    .add("content", note.getContent())
                    .add("category", note.getCategory().toString())
                    .add("createdAt", dateFormat.format(note.getCreatedAt()))
                    .build();
            try {
                session.getBasicRemote().sendText(jsonObject.toString());
                System.out.println("Note:" + note.getTitle() + " " + note.getContent());
            } catch (Exception e) {
                try {
                    session.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }

    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Post construct");
        NoteNotifier.getInstance().addObserver(this);
    }
}
