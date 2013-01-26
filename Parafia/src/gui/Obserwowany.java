package gui;

public interface Obserwowany {
    public void dodajObserwatora( Obserwator obserwator );
    public void usunObserwatora( Obserwator obserwator );
    public void powiadomObserwatorow(Obserwator obserwator);
}
