package br.edu.femass.gui;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.dao.DaoAluno;
import br.edu.femass.dao.DaoEmprestimo;
import br.edu.femass.dao.DaoExemplar;
import br.edu.femass.dao.DaoProfessor;
import br.edu.femass.model.Aluno;
import br.edu.femass.model.Emprestimo;
import br.edu.femass.model.Exemplar;
import br.edu.femass.model.Leitor;
import br.edu.femass.model.Professor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class EmprestimoController implements Initializable {
    @FXML
    private ListView<Exemplar> lstExemplar;

    @FXML
    private TableView<Emprestimo> tabelaEmprestimo = new TableView<Emprestimo>();

    @FXML
    private TableColumn<Emprestimo, Long> colID = new TableColumn<>();

    @FXML
    private TableColumn<Leitor, String> colLeitor = new TableColumn<>();

    @FXML
    private TableColumn<Exemplar, String> colExemplar = new TableColumn<>();

    @FXML
    private TableColumn<Emprestimo, LocalDate> colData = new TableColumn<>();

    @FXML
    private TableColumn<Emprestimo, LocalDate> colPrevDev = new TableColumn<>();

    @FXML
    private ComboBox<Aluno> cboxAluno;

    @FXML
    private ComboBox<Professor> cboxProfessor;

    private DaoAluno daoAluno = new DaoAluno();
    private DaoProfessor daoProfessor = new DaoProfessor();

    private DaoEmprestimo daoEmprestimo = new DaoEmprestimo();

    private DaoExemplar daoExemplar = new DaoExemplar();

    private Emprestimo emprestimo;

    private Exemplar exemplar;

    private Aluno aluno;

    private Professor professor;

    @FXML
    private void btnGravarAluno(ActionEvent event) {
        exemplar = lstExemplar.getSelectionModel().getSelectedItem();
        aluno = cboxAluno.getSelectionModel().getSelectedItem();
        emprestimo = new Emprestimo(aluno, exemplar);
        daoEmprestimo.inserir(emprestimo);
        preencherTabela();
    }

    @FXML
    private void btnGravarProfessor(ActionEvent event) {

        exemplar = lstExemplar.getSelectionModel().getSelectedItem();
        professor = cboxProfessor.getSelectionModel().getSelectedItem();
        emprestimo = new Emprestimo(professor, exemplar);
        daoEmprestimo.inserir(emprestimo);
        preencherTabela();
    }

    private void preencherCombo() {
        List<Aluno> alunos = daoAluno.buscarTodos();
        ObservableList<Aluno> dataAluno = FXCollections.observableArrayList(alunos);
        cboxAluno.setItems(dataAluno);

        List<Professor> professores = daoProfessor.buscarTodos();
        ObservableList<Professor> dataProfessor = FXCollections.observableArrayList(professores);
        cboxProfessor.setItems(dataProfessor);

    }

    private void preencherTabela() {
        List<Emprestimo> emprestimos = daoEmprestimo.buscarTodos();
        ObservableList<Emprestimo> data = FXCollections.observableArrayList(emprestimos);
        tabelaEmprestimo.setItems(data);
        tabelaEmprestimo.refresh();
    }

    private void preencherLista() {
        List<Exemplar> exemplares = daoExemplar.buscarTodos();
        ObservableList<Exemplar> data = FXCollections.observableArrayList(exemplares);
        lstExemplar.setItems(data);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colID.setCellValueFactory(
                new PropertyValueFactory<Emprestimo, Long>("id"));

        colLeitor.setCellValueFactory(
                new PropertyValueFactory<Leitor, String>("leitor"));

        colExemplar.setCellValueFactory(
                new PropertyValueFactory<Exemplar, String>("exemplar"));

        colData.setCellValueFactory(
                new PropertyValueFactory<Emprestimo, LocalDate>("dataEmprestimo"));

        colPrevDev.setCellValueFactory(
                new PropertyValueFactory<Emprestimo, LocalDate>("dataPrevistaDevolucao"));

        emprestimo = new Emprestimo();
        preencherCombo();
        preencherLista();
        preencherTabela();
    }
}