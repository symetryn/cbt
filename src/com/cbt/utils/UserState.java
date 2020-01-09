package com.cbt.utils;

/**
 * Singleton class for storing user state
 *
 * 
 */
public final class UserState {

    private static UserState instance;

    private String name;
    private Integer userId;
    private Integer level;
    private Integer semester;

    private UserState(String name, Integer userId, Integer level, Integer semester) {
        this.name = name;
        this.userId = userId;
        this.level = level;
        this.semester = semester;
    }

    /**
     * Get current stored instance
     * @return instance of the singleton class
     */
    public static UserState getInstance() {
        return instance;
    }

    /**
     * instantiating the singleton class
     *
     * @param name
     * @param userId
     * @param level
     * @param semester
     * @return created instance
     */
    public static UserState createInstance(String name, Integer userId, Integer level, Integer semester) {
        if (instance == null) {
            instance = new UserState(name, userId, level, semester);
            System.out.println("new user" + name);
        }

        return instance;
    }

    /**
     * resets the state
     */
    public void resetState() {
        System.out.println("state reset");
        name = null;
        userId = null;
        level = null;
        semester = null;
        instance=null;
    }

    /**
     *
     * @return string representation of class
     */
    @Override
    public String toString() {
        return "UserState name=" + name + " userId=" + userId + " level=" + level + " semester" + semester;
    }

    /**
     *
     * @return gives student name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return gives userID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     *
     * @return gives student Level
     */
    public Integer getLevel() {
        return level;
    }

    /**
     *
     * @return gives student semester
     */
    public Integer getSemester() {
        return semester;
    }
}
