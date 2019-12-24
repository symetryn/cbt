
package com.cbt.utils;


/**
 * Singleton class for storing user state
 * @author Symetryn
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
     *
     * @return instance of the singleton class
     */
    public static UserState getInstance() {
        return instance;
    }


    /**
     * instantiating the class if not exists
     * @param name
     * @param userId
     * @param level
     * @param semester
     * @return created instance 
     */
    public static UserState createInstance(String name, Integer userId, Integer level, Integer semester) {
        if (instance == null) {
            instance = new UserState(name, userId, level, semester);
        }
        return instance;
    }

    /**
     * resets the state
     */
    public void resetState() {
        name=null;
        userId=null;
        level=null;
        semester=null;
        
    }

    /**
     *
     * @return string representation of class
     */
    @Override
    public String toString() {
        return "UserState name=" + name + " userId=" + userId + " level=" + level + " semester" + semester;
    }

  
    public String getName() {
        if(name==null){
            return "no user";
        }
        return name;
    }

 
    public Integer getUserId() {
        return userId;
    }

    public Integer getLevel() {
        return level;
    }

    public Integer getSemester() {
        return semester;
    }
}
