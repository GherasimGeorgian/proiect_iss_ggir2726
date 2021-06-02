package service;

import domain.*;
import repository.*;
import utils.events.ChangeEvent;
import utils.events.ChangeEventType;
import utils.observer.Observable;
import utils.observer.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Service implements Observable<ChangeEvent> {
    private IClientRepository clientRepository;
    private IAdminRepository adminRepository;
    private ILocRepository locRepository;
    private IReprezentatieRepository reprezentatieRepository;
    private IDatePersonaleRepository datePersonaleRepository;
    private IRezervareRepository rezervareRepository;
    public Service(IClientRepository clientRepository, IAdminRepository adminRepository,ILocRepository locRepository, IReprezentatieRepository reprezentatieRepository,IDatePersonaleRepository datePersonaleRepository,IRezervareRepository rezervareRepository) {
        this.clientRepository = clientRepository;
        this.adminRepository =adminRepository;
        this.locRepository = locRepository;
        this.reprezentatieRepository = reprezentatieRepository;
        this.datePersonaleRepository = datePersonaleRepository;
        this.rezervareRepository = rezervareRepository;

    }
    private List<Observer<ChangeEvent>> observers=new ArrayList<>();

    @Override
    public void addObserver(Observer<ChangeEvent> e) {
        observers.add(e);

    }

    @Override
    public void removeObserver(Observer<ChangeEvent> e) {
        observers.remove(e);
    }
    @Override
    public void notifyObservers(ChangeEvent t) {
        observers.stream().forEach(x->x.update(t));
    }

    public Client loginClient(String username,String password){
        return clientRepository.loginClient(username,password);
    }
    public Admin loginAdmin(String username,String password){
        return adminRepository.loginAdmin(username,password);
    }
    public Iterable<Loc> getLocuri(){
        return locRepository.findAll();
    }
    public Loc getLoc(long id){
        return locRepository.findOne(id);
    }
    public Iterable<Reprezentatie> getReprezentatii(){
        return reprezentatieRepository.findAll();
    }
    public Iterable<Loc> getLocuribyReprezentatie(long id_repr){
        Predicate<Loc> byId= x->Long.toString(x.getId_reprezentatie()).equals(Long.toString(id_repr));
        Iterable<Loc> locuri = StreamSupport.stream(getLocuri().spliterator(), false)
                .filter(byId)
                .collect(Collectors.toList());
        return locuri;

    }
    public DatePersonale getDatePersonaleByClient(Client client){
        DatePersonale datapers = null;
        try {
            Predicate<DatePersonale> byId = x -> Long.toString(x.getId_client()).equals(Long.toString(client.getId()));
            datapers = StreamSupport.stream(datePersonaleRepository.findAll().spliterator(), false)
                    .filter(byId)
                    .collect(Collectors.toList()).stream().findFirst().get();
        }catch(Exception ex){
            return null;
        }
        return datapers;
    }
    public long getMaxIdDatePersonale(){
        return datePersonaleRepository.getMaxId();
    }
    public long getMaxIdRezervare(){
        return rezervareRepository.getMaxId();
    }
    public long getMaxIdReprezentatie(){
        return reprezentatieRepository.getMaxId();
    }
    public void salveazaDatePersonale(DatePersonale datepers){
        datePersonaleRepository.save(datepers);
    }
    public void actualizareStareLocREZERVAT(long id_loc){
        Loc loc = locRepository.findOne(id_loc);
        Loc locNou = new Loc(loc.getId(),loc.getRandul(),loc.getLoja(),loc.getNumar(),loc.getPret(),StareLoc.REZERVAT,loc.getId_reprezentatie());
        locRepository.update(loc,locNou);
    }
    public void actualizareStareLocLiber(long id_loc){
        Loc loc = locRepository.findOne(id_loc);
        Loc locNou = new Loc(loc.getId(),loc.getRandul(),loc.getLoja(),loc.getNumar(),loc.getPret(),StareLoc.LIBER,loc.getId_reprezentatie());
        locRepository.update(loc,locNou);
    }
    public void salvare_rezervare(Rezervare rezervare){
        this.actualizareStareLocREZERVAT(rezervare.getId_loc());
        rezervareRepository.save(rezervare);
        notifyObservers(new ChangeEvent(ChangeEventType.R_ADD, rezervare));

    }
    public Reprezentatie getReprezentatie(long id_reprezentatie){
        return reprezentatieRepository.findOne(id_reprezentatie);
    }
    public void adaugaReprezentatie(Reprezentatie repr){
        reprezentatieRepository.save(repr);
        notifyObservers(new ChangeEvent(ChangeEventType.S_ADD, repr));
    }
    public void stergeLoc(Loc loc){
        locRepository.delete(loc.getId());
    }
    public long getMaxIdLoc(){
        return locRepository.getMaxId();
    }
    public void adaugaLoc(Loc loc){
        locRepository.save(loc);
        notifyObservers(new ChangeEvent(ChangeEventType.L_ADD, loc));
    }
}
