package ro.tuc.tp.tema3.dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;


import ro.tuc.tp.tema3.connection.ConnectionFactory;


public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    /**
     * creează interogarea pentru baza de date
     * @param field campul din tabel
     * @return returnează stringul cu interogarea
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append("`ordermanagementdb`.`" + type.getSimpleName().toLowerCase() + "`");
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    /**
     * afisează din baza de date informațiile
     * @return returnează informația din baza de date
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "select * from " + type.getSimpleName() + ";";

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }


    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * creează o lista de obiecte din clasa T
     * @param resultSet contine datele din baza de date
     * @return returnează lista de obiecte
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T) ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     *execută interogarea creată pentru insert si face conexiunea cu baza de date
     * @param t
     * @return returneză un obiect care a fost inserat
     */
    public T insert(T t) {
        // TODO:
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createInsert(t);


        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.execute();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return t;
    }

    /**
     * se creează interogarea pentru insert in baza de date
     * @param t
     * @return returnează interogarea pentru baza de date
     */
    private String createInsert(T t) {
        String s = "INSERT INTO `ordermanagementdb`.`" + type.getSimpleName().toLowerCase() + "` (";
        try {
            Class cls = Class.forName("ro.tuc.tp.tema3.DataModels." + type.getSimpleName());
            Field[] fields = cls.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                s = s + "`" + fields[i].getName() + "`";
                if (i == fields.length - 1) {
                    s = s + ") ";

                } else {
                    s = s + ", ";
                }
            }
            s = s + "VALUES(";
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                try {
                    s = s + "\'" + fields[i].get(t) + "\'";
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                if (i == fields.length - 1) {
                    s = s + ") ;";

                } else {
                    s = s + ", ";
                }
            }
            System.out.println(s);
            return s;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
        return null;

    }

    /**
     * se creează si se execută interogarea pentru update la baza de date
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     */
    public T update(T t, int id) throws ClassNotFoundException, IllegalAccessException {
        // TODO:
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createUpdate(t,id);
        System.out.println(query);

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);

            statement.execute();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return t;
    }

    /**
     * se creează interogarea pentru a face update
     * @return returnează stringul cu interogarea
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     */
    private String createUpdate(T t, int id) throws ClassNotFoundException, IllegalAccessException {
        //Update `ordermanagementdb`.`+type.getSimpleName().toLowerCase()+` WHERE `id`=
       //UPDATE `ordermanagementdb`.`client` SET `id` = '5', `name` = 'bvc', `adresa` = 'asd' WHERE (`id` = '4');
        String s="Update `ordermanagementdb`.`"+type.getSimpleName().toLowerCase()+"` SET ";
        Class cls2 = Class.forName("ro.tuc.tp.tema3.DataModels." + type.getSimpleName());
        Field[] fields = cls2.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            if(i<fields.length-1)
                s = s + "`" + fields[i].getName() + "` = \'"+fields[i].get(t)+"\', ";
            else
                s = s + "`" + fields[i].getName() + "` = \'"+fields[i].get(t)+"\'";
        }
        s=s+" WHERE (`id`=\'"+id+"\');";
        return s;

    }

    /**
     * se creează si se execută interogarea pentru delete
     * @param t obiectul care se sterge
     */
    public void delete(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createDelete(t);


        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.execute();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * se creează interogarea pentru delete
     * @return returnează un string cu interogarea
     */
    private String createDelete(T t){
        //DELETE FROM `ordermanagementdb`.`+type.getSimpleName().toLowerCase()+` WHERE `id`=
        String s="DELETE FROM `ordermanagementdb`.`"+type.getSimpleName().toLowerCase()+"` WHERE `id`=";
        try {
            Field field = Class.forName("ro.tuc.tp.tema3.DataModels." + type.getSimpleName()).getDeclaredField("id");
            field.setAccessible(true);
            s = s +"\'" + field.get(t)+"\'";
            return s;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
