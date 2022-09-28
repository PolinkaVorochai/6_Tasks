package part_6.task_2.comparator;

import part_6.task_2.note.Note;

import java.util.Comparator;

public class DateEmailComparator implements Comparator<Note> {
    @Override
    public int compare(Note x, Note y) {
        int result = x.getEmail().compareTo(y.getEmail());
        if (result == 0) {
            return x.getDate().compareTo(y.getDate());
        }
        return result;
    }
}
