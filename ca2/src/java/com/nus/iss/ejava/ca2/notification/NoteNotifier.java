package com.nus.iss.ejava.ca2.notification;

import java.util.Observable;
import com.nus.iss.ejava.ca2.entity.Note;



/**
 *
 * @author mayuran
 */
public class NoteNotifier extends Observable{
    private static NoteNotifier noteNotifier;
    private NoteNotifier(){
        
    }
    
    public static NoteNotifier getInstance(){
        if(noteNotifier == null){
            noteNotifier = new NoteNotifier();
        }
        
        return noteNotifier;
    }

    public void notify(Note note){
        this.setChanged();
        this.notifyObservers(note);
    }
}
