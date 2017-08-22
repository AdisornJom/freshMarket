package com.fresh.market.jsf.common;

import com.fresh.market.core.ejb.entity.AdminUserRole;
import com.fresh.market.core.ejb.entity.Language;
import com.fresh.market.core.ejb.entity.SysSeleteitem;
import com.fresh.market.ejb.facade.ComboFacade;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Administrator
 */
@SessionScoped
@Named(ComboController.CONTROLLER_NAME)
public class ComboController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(ComboController.class);
    public static final String CONTROLLER_NAME = "comboController";

    @Inject
    private ComboFacade comboFacade;
    private List<AdminUserRole> adminUserRole;
    private List<SelectItem> sounds;
    private List<Language> langs;
    private List<SelectItem> equipments;

    @PostConstruct
    @Override
    public void init() {
        try {
            adminUserRole = comboFacade.findAdminUserRole();

            sounds = new ArrayList<>();
            sounds.add(new SelectItem("", "== No Sound =="));
            sounds.add(new SelectItem("2013horn_niw17bsn.mp3", "SOUND1"));
            sounds.add(new SelectItem("2013_l79IoH6G.mp3", "SOUND2"));
            sounds.add(new SelectItem("3oud_n139x2f8.mp3", "SOUND3"));
            sounds.add(new SelectItem("alarmanucl_yvmx86ee.mp3", "SOUND4"));
            sounds.add(new SelectItem("applering_7op6ebl9.mp3", "SOUND5"));
            sounds.add(new SelectItem("ayamnyanyi_2vtsz6fi.mp3", "SOUND6"));
            sounds.add(new SelectItem("bellstasiu_mxrl9czw.mp3", "SOUND7"));
            sounds.add(new SelectItem("bestwakeup_ien87yec.mp3", "SOUND8"));
            sounds.add(new SelectItem("birdsound_627s3tmr.mp3", "SOUND9"));
            sounds.add(new SelectItem("contestapa_d2pk4nrh.mp3", "SOUND10"));
            sounds.add(new SelectItem("counterstr_h9jwvve9.mp3", "SOUND11"));
            sounds.add(new SelectItem("dariperut_2ulq8iy5.mp3", "SOUND12"));
            sounds.add(new SelectItem("noti.mp3", "SOUND13"));
            sounds.add(new SelectItem("disparos_24rj1jmp.mp3", "SOUND14"));
            sounds.add(new SelectItem("dogbarking_kdlxrik6.mp3", "SOUND15"));
            sounds.add(new SelectItem("dukesofhaz_c3ymalyc.mp3", "SOUND16"));
            sounds.add(new SelectItem("frogs_kmfsz41r.mp3", "SOUND17"));
            sounds.add(new SelectItem("gatito_d16xj1h6.mp3", "SOUND18"));
            sounds.add(new SelectItem("gunshot_dv7mvxsr.mp3", "SOUND19"));
            sounds.add(new SelectItem("gussa_2bsytv3s.mp3", "SOUND20"));
            sounds.add(new SelectItem("horrortone_3au6lnma.mp3", "SOUND21"));
            sounds.add(new SelectItem("iphone5_85da1cum.mp3", "SOUND22"));
            sounds.add(new SelectItem("iphonering_vcarkmba.mp3", "SOUND23"));
            sounds.add(new SelectItem("islamicspe_ef6hk9fz.mp3", "SOUND24"));
            sounds.add(new SelectItem("jacksparro_forsryre.mp3", "SOUND25"));
            sounds.add(new SelectItem("killbill-w_rnve3dc5.mp3", "SOUND26"));
            sounds.add(new SelectItem("mexicanmaf_3b5ihk9i.mp3", "SOUND27"));
            sounds.add(new SelectItem("morningala_c4dx4c77.mp3", "SOUND28"));
            sounds.add(new SelectItem("newnokiaip_bh9ao7d3.mp3", "SOUND29"));
            sounds.add(new SelectItem("nextel_c7f1fafk.mp3", "SOUND30"));
            sounds.add(new SelectItem("policesire_ne78qlc2.mp3", "SOUND31"));
            sounds.add(new SelectItem("psicosisen_8tnhuk95.mp3", "SOUND32"));
            sounds.add(new SelectItem("ringefect_5prfnnf1.mp3", "SOUND33"));
            sounds.add(new SelectItem("shaunmbeek_kdpevaer.mp3", "SOUND34"));
            sounds.add(new SelectItem("skyperingt_49hh4h8m.mp3", "SOUND35"));
            sounds.add(new SelectItem("starwars_lvlpu7zn.mp3", "SOUND36"));
            sounds.add(new SelectItem("titanic_8atppqd1.mp3", "SOUND37"));
            sounds.add(new SelectItem("trainhorn_cph8o7cc.mp3", "SOUND38"));
            sounds.add(new SelectItem("veryfunnyr_nk43an6z.mp3", "SOUND39"));
            sounds.add(new SelectItem("woodywoodp_kive2jus.mp3", "SOUND40"));

           
            langs = comboFacade.findLanguageList();
            
            List<SysSeleteitem> seleteItems = comboFacade.findSysSeleteitemByCriteria("equipment");
            equipments = new ArrayList<>();
            if(null!=seleteItems && seleteItems.size()>0){
                for(SysSeleteitem detail:seleteItems)
                    equipments.add(new SelectItem(detail.getTypeKey(),detail.getTypeName()));
            }

        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }
    }

    public List<AdminUserRole> getAdminUserRole() {
        return adminUserRole;
    }

    public void setAdminUserRole(List<AdminUserRole> adminUserRole) {
        this.adminUserRole = adminUserRole;
    }

    public List<SelectItem> getSounds() {
        return sounds;
    }

    public List<Language> getLangs() {
        return langs;
    }

    public void setLangs(List<Language> langs) {
        this.langs = langs;
    }
    
    public List<SelectItem> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<SelectItem> equipments) {
        this.equipments = equipments;
    }
}
