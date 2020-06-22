package backend;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.sql.*;

public class GeneralBilling {
    private long id = 0L;
    private String name = null;
    private double cost = 0.0;
    private Date createdAt = new Date();

    static SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public GeneralBilling() {}
    
    public GeneralBilling(String name) {
        this.name = name;
    }

    public String toString() {
        String message = "";
        message += "General Billing ID: " + id + "\n";
        message += "Name: " + name + "\n";
        message += "Cost: " + cost + "\n";
        message += "Created At: " + createdAt;
        return message;
    }

    public void setId(long id) { this.id = id; }
    public long getId() { return id; }

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    public void setCost(double cost) { this.cost = cost; }
    public double getCost() { return cost; }

    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
    public Date getCreatedAt() { return createdAt; }

    public static long addGeneralBilling(GeneralBilling generalBilling) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = MainDataConnection.connection.prepareStatement("INSERT INTO General_Billing VALUES (0, ?, ?, ?);");
            preparedStatement.setString(1, generalBilling.getName());
            preparedStatement.setDouble(2, generalBilling.getCost());
            preparedStatement.setString(3, dff.format(new Date()));
            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                System.out.println("Added record " + result);
                preparedStatement.close();
                preparedStatement = MainDataConnection.connection.prepareStatement("SELECT MAX(id) FROM General_Billing;");
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                long id = resultSet.getLong(1);
                generalBilling.setId(id);
                return id;
            } else {
                System.out.println("No records added");
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public static boolean removeGeneralBilling(long id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = MainDataConnection.connection.prepareStatement("DELETE FROM General_Billing WHERE id = ?;");
            preparedStatement.setLong(1, id);
            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean updateGeneralBilling(GeneralBilling generalBilling) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = MainDataConnection.connection.prepareStatement("UPDATE General_Billing SET name = ?, cost = ? WHERE id = ?;");
            preparedStatement.setString(1, generalBilling.getName());
            preparedStatement.setDouble(2, generalBilling.getCost());
            preparedStatement.setLong(3, generalBilling.getId());
            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean isGeneralBillingPresent(long id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = MainDataConnection.connection.prepareStatement("SELECT * FROM General_Billing WHERE id = ?;");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            int count = 0;
            while (resultSet.next()) {
                ++count;
            }
            if (count == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static GeneralBilling getGeneralBilling(long id) {
        PreparedStatement preparedStatement = null;
        GeneralBilling generalBilling = null;
        try {
            preparedStatement = MainDataConnection.connection.prepareStatement("SELECT * FROM General_Billing WHERE id = ?;");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                generalBilling = new GeneralBilling();
                generalBilling.setId(resultSet.getLong(1));
                generalBilling.setName(resultSet.getString(2));
                generalBilling.setCost(resultSet.getDouble(3));
                generalBilling.setCreatedAt(resultSet.getDate(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return generalBilling;
    }

    public static boolean updateGeneralBillingCost(long id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = MainDataConnection.connection.prepareStatement("SELECT cost FROM General_Medicine_Outlet WHERE bill_id = ?;");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            double cost = 0.0;
            while (resultSet.next()) {
                cost += resultSet.getDouble(1);
            }
            cost = (Math.round(cost*10.0)/10.0);
            preparedStatement.close();
            preparedStatement = MainDataConnection.connection.prepareStatement("UPDATE General_Billing SET cost = ? WHERE id = ?;");
            preparedStatement.setDouble(1, cost);
            preparedStatement.setLong(2, id);
            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static double getGeneralBillTotalBetweenDates(String from_date, String to_date) {
        PreparedStatement preparedStatement = null;
        double total = 0.0;
        try {
            preparedStatement = MainDataConnection.connection.prepareStatement("SELECT cost FROM General_Billing WHERE DATE(created_at) BETWEEN ? AND ?;");
            preparedStatement.setString(1, from_date);
            preparedStatement.setString(2, to_date);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                total += resultSet.getDouble(1);
            }
            total = (Math.round(total*10.0)/10.0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return total;
    }
}