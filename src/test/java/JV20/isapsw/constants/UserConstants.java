package JV20.isapsw.constants;

public class UserConstants {
    public static final String FIRST_NAME = "Marko";
    public static final String LAST_NAME = "Markovic";
    public static final Long ID = 1L;

    public static final Long DB_ID = 7L;
    public static final String DB_FIRST_NAME = "Igor";
    public static final String DB_LAST_NAME = "Jovin";
    public static final String DB_CARD_NUMBER = "ra7-2014";

    //number of students whose last name is DB_LAST_NAME
    public static final int DB_COUNT_WITH_LAST_NAME = 2;

    //id of a student who is referenced by other entities
    public static final Long DB_ID_REFERENCED = 1L;

    //number of courses enrolled by student with ID DB_ID_REFERENCED
    public static final int DB_COUNT_STUDENT_COURSES = 2;

    //number of exams for student with ID DB_ID_REFERENCED
    public static final int DB_COUNT_STUDENT_EXAMS = 2;

    public static final int DB_COUNT = 12;
    public static final int PAGE_SIZE = 5;
}
