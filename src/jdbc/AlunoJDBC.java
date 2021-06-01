package jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import entities.Aluno;

public class AlunoJDBC {
	
	Connection con;
	String sql;
	PreparedStatement pst;
	
	
	public void salvar(Aluno a) throws IOException {
		
		try {
			Connection con = db.getConexao();
			
			sql = "INSERT INTO aluno (nome, sexo, dt_nasc) VALUES ( ?,  ?, ?)";
			
			pst = con.prepareStatement(sql);
			pst.setString(1, a.getNome());
			pst.setString(2, a.getSexo());
			
			Date dataSql = new Date(a.getDt_nasc().getTime());
			pst.setDate(3, dataSql);
			
			pst.executeUpdate();
			System.out.println("\nCadastro do aluno realizado com sucesso!");
			
			db.fechaConexao();
			System.out.println("Conex�o fechada com sucesso !");
		}
		catch (SQLException e) {
			
			System.out.println(e);
		}
		
	}
	
	public List<Aluno> listar() throws IOException {
		try {
			List<Aluno> alunos = new ArrayList<Aluno>();
			
			Connection con = db.getConexao();
	
			String sql = "select * from aluno";
			
			pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				Aluno aluno = new Aluno();
				aluno.setId(rs.getInt("id"));
				aluno.setDt_nasc(rs.getDate("dt_nasc"));
				aluno.setSexo(rs.getString("sexo"));
				aluno.setNome(rs.getString("nome"));
				alunos.add(aluno);
			}
			pst.execute();
			pst.close();
			return alunos;
		}catch(SQLException e) {
			throw new RuntimeException(e);
			
		}
		
	}
	
	public void apagar(int id) throws IOException {
		try {
			Connection con = db.getConexao();
	
			
			String sql = "delete from aluno where id=?";
			
			pst = con.prepareStatement(sql);	
			pst.setInt(1, id);
			pst.execute();
			
			
			pst.execute();
			System.out.println("\nRemocao do aluno realizado com sucesso!");
			
			db.fechaConexao();
			System.out.println("Conex�o fechada com sucesso !");
		
			
		}catch(SQLException e) {
			System.out.print(e.getMessage());
		}
	}
	
	public void alterar(Aluno a) throws IOException{
		try {
			
			String sql = "UPDATE aluno SET ";
			Connection con = db.getConexao();
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	
			if(a.getNome() != null) {
				sql += "sexo = '"+a.getNome()+ "'";
			}
			
			if(a.getDt_nasc() != null) {
				sql += "dt_nasc = '"+formato.format(a.getDt_nasc())+ "'";
			}
			
			if(a.getSexo() != null) { 
				sql += "sexo = '"+a.getSexo()+ "'";
			}
			
			sql += " WHERE id = "+a.getId()+";";
			
	

			
			pst = con.prepareStatement(sql);

		
			pst.execute();
			pst.close();
			System.out.println("\nAlteracao do aluno realizado com sucesso!");

		}catch(SQLException e) {
			System.out.print(e.getMessage());
		}
	}
	
	public Aluno getAluno(int id) throws IOException {
		try {
			
			Connection con = db.getConexao();
			String sql = "select * from aluno where id =?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				Aluno aluno = new Aluno();
				aluno.setDt_nasc(rs.getDate("dt_nasc"));
				aluno.setNome(rs.getString("nome"));
				aluno.setSexo(rs.getString("sexo"));
				return aluno;
			}
			return null;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}

	}
}

