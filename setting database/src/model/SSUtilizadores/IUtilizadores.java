package model.SSUtilizadores;

public interface IUtilizadores {
    void atualizaPreferencias(Preferencia prefs);
    void adicionaFavorito(Integer id);
    void removeFavorito(Integer id);
    String getCoordenadas();
    Preferencia getPreferencia();

}

