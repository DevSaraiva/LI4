package model.SSUtilizadores;

import java.io.Serializable;
import java.util.Set;

public class SSUtilizadorFacade implements IUtilizadores, Serializable {
    private Utilizador utilizador;


    public SSUtilizadorFacade(Preferencia p, Set<Integer> favoritos){
        this.utilizador = new Utilizador(p,favoritos);
    }

    public void atualizaPreferencias(Preferencia prefs) {
        utilizador.setPreferencia(prefs);
    }

    public void adicionaFavorito(Integer id){
        utilizador.adicionaFavorito(id);
    }

    public void removeFavorito(Integer id){
        utilizador.removeFavotito(id);
    }

    @Override
    public String getCoordenadas() {
        return utilizador.getCoordenadas();
    }


    public Preferencia getPreferencia(){
        return this.utilizador.getPreferencia();
    }


}
