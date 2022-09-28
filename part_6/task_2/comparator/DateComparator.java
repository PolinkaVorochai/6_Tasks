package part_6.task_2.comparator;

import part_6.task_2.note.Note;

import java.util.Comparator;

public class DateComparator implements Comparator<Note> {
    @Override
    public int compare(Note x, Note y) {
        return x.getDate().compareTo(y.getDate());
    }
}
