package br.edu.femass.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.dao.DaoProfessor;
import br.edu.femass.model.Professor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProfessorController implements Initializable {

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtEndereco;

    @FXML
    private TextField txtTelefone;

    @FXML
    private TextField txtDisciplina;
    @FXML
    private TableView<Professor> tabela = new TableView<Professor>();

    @FXML
    private TableColumn<Professor, Long> colTelefone = new TableColumn<>();

    @FXML
    private TableColumn<Professor, String> colNome = new TableColumn<>();

    @FXML
    private TableColumn<Professor, String> colDisciplina = new TableColumn<>();

    @FXML
    private Button BtnIncluir;

    @FXML
    private Button BtnAlterar;

    @FXML
    private Button BtnExcluir;

    @FXML
    private Button BtnGravar;

    private DaoProfessor dao = new DaoProfessor();
    private Professor professor = new Professor();
    private Boolean incluindo;

    @FXML
    private void gravar_click(ActionEvent event) {
        professor.setNome(txtNome.getText());
        professor.setTelefone(txtTelefone.getText());
        professor.setEndereco(txtEndereco.getText());
        professor.setDisciplina(txtDisciplina.getText());

        if (incluindo) {
            dao.inserir(professor);
        } else {
            dao.alterar(professor);
        }

        preencher_tabela();
        editar(false);
    }

    @FXML
    private void incluir_click(ActionEvent event) {
        editar(true);
        preencher_tabela();
        incluindo = true;

        professor = new Professor();
    }

    @FXML
    private void alterar_click(ActionEvent event) {
        editar(true);
        preencher_tabela();
        incluindo = false;
    }

    @FXML
    private void excluir_click(ActionEvent event) {
        dao.apagar(professor);

        preencher_tabela();
    }

    private void editar(boolean habilitar) {
        BtnGravar.setDisable(!habilitar);
        BtnAlterar.setDisable(habilitar);
        BtnIncluir.setDisable(habilitar);
        BtnExcluir.setDisable(habilitar);
    }

    private void preencher_tabela() {
        List<Professor> professores = dao.buscarTodos();
        ObservableList<Professor> data = FXCollections.observableArrayList(professores);
        tabela.setItems(data);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colTelefone.setCellValueFactory(new PropertyValueFactory<Professor, Long>("telefone"));
        colNome.setCellValueFactory(new PropertyValueFactory<Professor, String>("nome"));
        colDisciplina.setCellValueFactory(new PropertyValueFactory<Professor, String>("disciplina"));
        preencher_tabela();

    }

}