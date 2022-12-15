package br.edu.femass.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.dao.DaoAutor;
import br.edu.femass.model.Autor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AutorController implements Initializable {
    
    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtSobrenome;

    @FXML
    private TextField txtNacionalidade;

    @FXML
    private TableView<Autor> tabelaAutor = new TableView<Autor>();

    @FXML
    private TableColumn<Autor,Long> colID = new TableColumn<>();

    @FXML
    private TableColumn<Autor,String> colNome = new TableColumn<>();

    @FXML
    private TableColumn<Autor,String> colSobrenome = new TableColumn<>();

    @FXML
    private TableColumn<Autor,String> colNacionalidade = new TableColumn<>();

    private DaoAutor dao = new DaoAutor();
    private Autor autor = new Autor();
    
    @FXML
    private void btnGravar(ActionEvent event) {
        autor.setNome(txtNome.getText());
        autor.setSobrenome(txtSobrenome.getText());
        autor.setNacionalidade(txtNacionalidade.getText());
        dao.inserir(autor);
        preencher_tabela();
    }

    private void preencher_tabela(){
        List<Autor> autores = dao.buscarTodos();

        ObservableList<Autor> data = FXCollections.observableArrayList(autores);
        tabelaAutor.setItems(data); 
        tabelaAutor.refresh();  
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colID.setCellValueFactory(
            new PropertyValueFactory<Autor,Long>("id")
        );

        colNome.setCellValueFactory(
            new PropertyValueFactory<Autor,String>("nome")
        );
        
        colSobrenome.setCellValueFactory(
            new PropertyValueFactory<Autor,String>("sobrenome")
        );

        colNacionalidade.setCellValueFactory(
            new PropertyValueFactory<Autor,String>("nacionalidade")
        );
        preencher_tabela();
    }    
}
