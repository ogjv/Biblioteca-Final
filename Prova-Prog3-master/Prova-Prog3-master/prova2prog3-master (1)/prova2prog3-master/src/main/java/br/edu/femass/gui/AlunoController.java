package br.edu.femass.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.dao.DaoAluno;
import br.edu.femass.model.Aluno;
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

public class AlunoController implements Initializable {
    private DaoAluno dao = new DaoAluno();
    private Aluno aluno;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtEndereco;

    @FXML
    private TextField txtTelefone;

    @FXML
    private TextField txtMatricula;

    @FXML
    private TableView<Aluno> tabela = new TableView<Aluno>();

    @FXML
    private TableColumn<Aluno, Long> colTelefone = new TableColumn<>();

    @FXML
    private TableColumn<Aluno, String> colNome = new TableColumn<>();

    @FXML
    private TableColumn<Aluno, String> colMatricula = new TableColumn<>();

    @FXML
    private Button BtnIncluir;

    @FXML
    private Button BtnAlterar;

    @FXML
    private Button BtnExcluir;

    @FXML
    private Button BtnGravar;

    private Boolean incluindo;

    @FXML
    private void gravar_click(ActionEvent event) {
        Aluno aluno = new Aluno(txtNome.getText(), txtEndereco.getText(),
                txtTelefone.getText(), txtMatricula.getText());
        if (incluindo) {
            dao.inserir(aluno);
        } else {
            dao.alterar(aluno);
        }

        preencher_tabela();
        editar(false);
    }

    @FXML
    private void incluir_click(ActionEvent event) {
        editar(true);
        preencher_tabela();
        incluindo = true;

        aluno = new Aluno();
    }

    @FXML
    private void alterar_click(ActionEvent event) {
        editar(true);
        preencher_tabela();
        incluindo = false;
    }

    @FXML
    private void excluir_click(ActionEvent event) {
        dao.apagar(aluno);

        preencher_tabela();
    }

    private void editar(boolean habilitar) {
        BtnGravar.setDisable(!habilitar);
        BtnAlterar.setDisable(habilitar);
        BtnIncluir.setDisable(habilitar);
        BtnExcluir.setDisable(habilitar);
    }

    private void preencher_tabela() {
        List<Aluno> alunos = dao.buscarTodos();
        ObservableList<Aluno> data = FXCollections.observableArrayList(alunos);
        tabela.setItems(data);
        tabela.refresh();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colTelefone.setCellValueFactory(new PropertyValueFactory<Aluno, Long>("telefone"));
        colNome.setCellValueFactory(new PropertyValueFactory<Aluno, String>("nome"));
        colMatricula.setCellValueFactory(new PropertyValueFactory<Aluno, String>("matricula"));
        preencher_tabela();

    }

}