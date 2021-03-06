package Kubaner.Logic;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SubjectList implements Iterable<Subject> {

    List<Subject> subjects;

    public SubjectList() {
        subjects = new LinkedList<>();
    }

    /**
     * Adds a subject to the list.
     *
     * @param subject The new subject that will be
     *                added to the list.
     */
    void add(Subject subject) {
        for(int i = 0; i < size(); i++) {
            if(subjects.get(i).getName().compareTo(subject.getName()) >= 1) {
                subjects.add(i, subject);
                return;
            }
        }
        // If the subject hasn't been added to the list
        // it will be added as the last subject.
        subjects.add(subject);
    }

    /**
     * Creates a new subject with the given name
     * and add it to the list.
     *
     * @param name The name of the subject.
     * @return Returns the newly created Subject
     */
    public Subject create(String name) {
        if(name == null || name.equals(""))
            throw new IllegalArgumentException("Name was null or empty");
        Subject newSubject = new Subject(name);
        add(newSubject);
        return newSubject;
    }

    /**
     * Gets a subject by index.
     *
     * @param index The index of the subject.
     * @return Returns the subject.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    public Subject get(int index) throws IndexOutOfBoundsException {
        if(index < 0 || index >= size())
            throw new IndexOutOfBoundsException("The index was out of bounds.");
        return subjects.get(index);
    }

    /**
     * Gets the subject list as an array.
     *
     * @return Returns the subject list as an array.
     */
    public Subject[] toArray() {
        return subjects.toArray(new Subject[size()]);
    }

    /**
     * Checks if a subject exists in the list.
     *
     * @param subject The subject that is looked for.
     * @return Returns true if the subject exists and false
     *         if it doesn't.
     */
    public boolean exist(Subject subject) {
        return subjects.contains(subject);
    }

    /**
     * Deletes a subject from the list by index.
     *
     * @param index The index of the subject that will be removed.
     * @return Returns true if the subject was deleted and returns
     *         false if it wasn't deleted.
     */
    public boolean delete(int index) {
        if(index < 0 || index >= size())
            return false;
        subjects.remove(index);
        return true;
    }

    /**
     * Gets the size of the list.
     *
     * @return Returns the size of the list.
     */
    public int size() {
        return subjects.size();
    }

    /**
     * Returns the iterator of the SubjectList
     *
     * @return Returns the iterator.
     */
	@Override
	public Iterator<Subject> iterator() {
        return subjects.iterator();
	}
}
