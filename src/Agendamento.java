import java.time.LocalDateTime;

public class Agendamento {
    private Frete frete;
    LocalDateTime dataAgendada;
    LocalDateTime dataRealizada;
    public Agendamento(Frete frete, LocalDateTime dataAgendada) {
    this.frete = frete;
    this.dataAgendada = dataAgendada;
    }
    public void confirmarEntrega(LocalDateTime dataChegada) {
        this.dataRealizada = dataChegada;
    }
    public double calcularMulta() {

        if (dataRealizada == null) {
            return 0.0;
        }

        if(dataRealizada.isAfter(dataAgendada)) {
            return 100.00;
        } else {
            return 0.0;
        }
    }
}
