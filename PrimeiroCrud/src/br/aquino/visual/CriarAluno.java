package br.aquino.visual;

import br.aquino.dao.AlunoDAOImpl;
import br.aquino.inferfaces.AlunoDAO;
import br.aquino.model.Aluno;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;




public class CriarAluno extends JFrame {

	private static final long serialVersionUID = -9157481906941039956L;
	private final DefaultTableModel modelo;
	private JPanel panel;
	private JButton btSalvar;
	private JButton btLimpar;
	private JLabel lbNome;
	private JLabel lbEmail;
	private JLabel lbCpf;
	private JTextField txNome;
	private JTextField txEmail;
        private JFormattedTextField txCpf;
	
	private final static MaskFormatter MASCARA_CPF = new MaskFormatter();

	CriarAluno(DefaultTableModel modelo) {
		super("Cadastro Pessoas");
		criarJanela();
		this.modelo = modelo;

	}

	private void criarJanela() {

		try {
			MASCARA_CPF.setMask("###.###.###-##");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		btSalvar = new JButton("Salvar");
		btLimpar = new JButton("Limpar");
		lbNome = new JLabel("Nome.:   ");
		lbEmail = new JLabel("email:   ");
		lbCpf = new JLabel("CPF: ");
                        
                        
		txNome = new JTextField(10);
		txEmail = new JTextField();
		txCpf = new JFormattedTextField(MASCARA_CPF);

		panel = new JPanel(new GridLayout(4, 2, 1, 1));
		panel.add(lbNome);
		panel.add(txNome);
		panel.add(lbEmail);
		panel.add(txEmail);
                panel.add(lbCpf);
                panel.add(txCpf);
		panel.add(btLimpar);
		panel.add(btSalvar);

		getContentPane().add(panel);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(300,200);
		setVisible(true);
		btSalvar.addActionListener(new SalvarListener());
		btLimpar.addActionListener(new LimparListener());
	}

	private class SalvarListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String nome = txNome.getText().trim();
			String email = txEmail.getText().trim();
			String cpf = txCpf.getText().trim();
			boolean verificar = !nome.isEmpty() && nome.length() > 0;
			verificar &= !email.isEmpty() && email.length() > 0;
			verificar &= !cpf.isEmpty() && cpf.length() > 0;
			if (verificar) {
				Aluno a = new Aluno(email, nome, cpf);

				AlunoDAO dao = new AlunoDAOImpl();
				dao.create(a);

				JanelaPrincipal.pesquisar(modelo);
				setVisible(false);
			} else {
				txNome.setText("");
				txCpf.setText("");
				txEmail.setText("");
				JOptionPane.showMessageDialog(CriarAluno.this, "Informe os valores corretamente", getTitle(),
						JOptionPane.WARNING_MESSAGE);
			}

		}

	}

	private class LimparListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			txNome.setText("");
			txEmail.setText("");
			txCpf.setText("");
                       
		}

	}
}
