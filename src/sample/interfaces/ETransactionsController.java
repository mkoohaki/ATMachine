package sample.interfaces;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.database.AccountDatabase;
import sample.partials.ModelTable;

import java.io.IOException;
import java.sql.SQLException;

public class ETransactionsController{
    @FXML
    javafx.scene.control.Button onClick;

    @FXML
    private TableView<ModelTable> table;

    @FXML
    private TableColumn<ModelTable, String> col_accountF, col_accountT, col_amount, col_date;

    @FXML
    private TableColumn<ModelTable, Button> col_button;


    ObservableList observableList = FXCollections.observableArrayList();

    String accountNum;

    void setInfo(String accountNumber) throws SQLException, IOException {
        accountNum = accountNumber;

        AccountDatabase db = new AccountDatabase();
        observableList = db.eTransactions(accountNum);

        col_accountF.setCellValueFactory(new PropertyValueFactory<>("account_from"));
        col_accountT.setCellValueFactory(new PropertyValueFactory<>("account_to"));
        col_amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_button.setCellValueFactory(new PropertyValueFactory<>("button"));

        table.setItems(observableList);
    }

    @FXML
    private void back(ActionEvent event) throws SQLException, IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("account.fxml"));
        Parent root = loader.load();
        AccountController accountController = loader.getController();
        accountController.setInfo(accountNum);
        onClick.getScene().setRoot(root);
    }
}
