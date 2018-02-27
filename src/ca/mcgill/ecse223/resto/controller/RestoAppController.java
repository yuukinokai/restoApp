package ca.mcgill.ecse223.resto.model;
import java.util.*;
import java.sql.Date;

public interface RestoAppController 
{
  public void moveTable(Table table, int x, int y);
  public void rotateTable(Table table);
}