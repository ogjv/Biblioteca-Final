package br.edu.femass.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import br.edu.femass.dao.DaoAutor;
import br.edu.femass.dao.DaoLivro;
import br.edu.femass.model.Autor;
import br.edu.femass.model.Livro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class LivroController implements Initializable {

    @FXML
    private TableView<Livro> tabela = new TableView<Livro>();

    @FXML
    private TableColumn<Livro, Long> colId = new TableColumn<>();

    @FXML
    private TableColumn<Autor, String> colAutor = new TableColumn<>();

    @FXML
    private TableColumn<Livro, String> colTitulo = new TableColumn<>();

    @FXML
    private ComboBox<Autor> cboAutor;

    @FXML
    private Button BtnIncluir;

    @FXML
    private Button BtnAlterar;

    @FXML
    private Button BtnExcluir;

    @FXML
    private Button BtnGravar;

    private Boolean incluindo;

    private DaoLivro dao = new DaoLivro();

    @FXML
    private TextField txtTitulo;

    private DaoAutor daoAutor = new DaoAutor();

    private Livro livro = new Livro();

    @FXML
    private void gravar_click(ActionEvent event) {
        livro.setTitulo(txtTitulo.getText());
        livro.setAutor(cboAutor.getSelectionModel().getSelectedItem());
        if (incluindo) {
            dao.inserir(livro);
        } else {
            dao.alterar(livro);
        }

        preencher_tabela();
        editar(false);
    }

    @FXML
    private void incluir_click(ActionEvent event) {
        editar(true);
        preencherCombo();
        preencher_tabela();
        incluindo = true;

        livro = new Livro();
    }

    @FXML
    private void alterar_click(ActionEvent event) {
        editar(true);
        preencherCombo();
        preencher_tabela();
        incluindo = false;
    }

    @FXML
    private void excluir_click(ActionEvent event) {
        dao.apagar(livro);

        preencher_tabela();
    }

    private void editar(boolean habilitar) {
        BtnGravar.setDisable(!habilitar);
        BtnAlterar.setDisable(habilitar);
        BtnIncluir.setDisable(habilitar);
        BtnExcluir.setDisable(habilitar);
    }

    private void preencher_tabela() {
        List<Livro> livros = dao.buscarTodos();
        ObservableList<Livro> data = FXCollections.observableArrayList(livros);
        tabela.setItems(data);
    }

    private void preencherCombo() {
        List<Autor> autores = daoAutor.buscarTodos();
        ObservableList<Autor> data = FXCollections.observableArrayList(autores);
        cboAutor.setItems(data);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colId.setCellValueFactory(new PropertyValueFactory<Livro, Long>("id"));
        colAutor.setCellValueFactory(new PropertyValueFactory<Autor, String>("autor"));
        colTitulo.setCellValueFactory(new PropertyValueFactory<Livro, String>("titulo"));
        preencher_tabela();
        preencherCombo();
    }

}
