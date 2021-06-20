package com.google.appinventor.components.runtime;

import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.collect.Maps;
import com.google.appinventor.components.runtime.util.ExternalTextToSpeech;
import com.google.appinventor.components.runtime.util.ITextToSpeech;
import com.google.appinventor.components.runtime.util.InternalTextToSpeech;
import com.google.appinventor.components.runtime.util.SdkLevel;
import com.google.appinventor.components.runtime.util.YailList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;

@DesignerComponent(category = ComponentCategory.MEDIA, description = "The TestToSpeech component speaks a given text aloud.  You can set the pitch and the rate of speech. <p>You can also set a language by supplying a language code.  This changes the pronunciation of words, not the actual language spoken.  For example, setting the language to French and speaking English text will sound like someone speaking English (en) with a French accent.</p> <p>You can also specify a country by supplying a country code. This can affect the pronunciation.  For example, British English (GBR) will sound different from US English (USA).  Not every country code will affect every language.</p> <p>The languages and countries available depend on the particular device, and can be listed with the AvailableLanguages and AvailableCountries properties.</p>", iconName = "images/textToSpeech.png", nonVisible = true, version = 5)
@SimpleObject
public class TextToSpeech extends AndroidNonvisibleComponent implements Component, OnStopListener, OnResumeListener, OnDestroyListener {
  private static final String LOG_TAG = "TextToSpeech";
  
  private static final Map<String, Locale> iso3CountryToLocaleMap;
  
  private static final Map<String, Locale> iso3LanguageToLocaleMap = Maps.newHashMap();
  
  private YailList allCountries;
  
  private YailList allLanguages;
  
  private String country;
  
  private ArrayList<String> countryList;
  
  private boolean isTtsPrepared;
  
  private String iso2Country;
  
  private String iso2Language;
  
  private String language;
  
  private ArrayList<String> languageList;
  
  private float pitch;
  
  private boolean result;
  
  private float speechRate;
  
  private final ITextToSpeech tts;
  
  static {
    iso3CountryToLocaleMap = Maps.newHashMap();
    initLocaleMaps();
  }
  
  public TextToSpeech(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer.$form());
    ExternalTextToSpeech externalTextToSpeech;
    InternalTextToSpeech internalTextToSpeech;
    boolean bool;
    String str;
    this.pitch = 1.0F;
    this.speechRate = 1.0F;
    this.result = false;
    Language("");
    Country("");
    if (SdkLevel.getLevel() < 4) {
      bool = true;
    } else {
      bool = false;
    } 
    StringBuilder stringBuilder = (new StringBuilder()).append("Using ");
    if (bool) {
      str = "external";
    } else {
      str = "internal";
    } 
    Log.v("TextToSpeech", stringBuilder.append(str).append(" TTS library.").toString());
    ITextToSpeech.TextToSpeechCallback textToSpeechCallback = new ITextToSpeech.TextToSpeechCallback() {
        public void onFailure() {
          TextToSpeech.access$002(TextToSpeech.this, false);
          TextToSpeech.this.AfterSpeaking(false);
        }
        
        public void onSuccess() {
          TextToSpeech.access$002(TextToSpeech.this, true);
          TextToSpeech.this.AfterSpeaking(true);
        }
      };
    if (bool) {
      externalTextToSpeech = new ExternalTextToSpeech(paramComponentContainer, textToSpeechCallback);
    } else {
      internalTextToSpeech = new InternalTextToSpeech(externalTextToSpeech.$context(), textToSpeechCallback);
    } 
    this.tts = (ITextToSpeech)internalTextToSpeech;
    this.form.registerForOnStop(this);
    this.form.registerForOnResume(this);
    this.form.registerForOnDestroy(this);
    this.form.setVolumeControlStream(3);
    this.isTtsPrepared = false;
    this.languageList = new ArrayList<String>();
    this.countryList = new ArrayList<String>();
    this.allLanguages = YailList.makeList(this.languageList);
    this.allCountries = YailList.makeList(this.countryList);
  }
  
  private void getLanguageAndCountryLists() {
    if (SdkLevel.getLevel() >= 4) {
      for (Locale locale : Locale.getAvailableLocales()) {
        if (this.tts.isLanguageAvailable(locale) != -2) {
          String str1 = locale.getLanguage();
          String str2 = locale.getISO3Country();
          if (!str1.equals("") && !this.languageList.contains(str1))
            this.languageList.add(str1); 
          if (!str2.equals("") && !this.countryList.contains(str2))
            this.countryList.add(str2); 
        } 
      } 
      Collections.sort(this.languageList);
      Collections.sort(this.countryList);
      this.allLanguages = YailList.makeList(this.languageList);
      this.allCountries = YailList.makeList(this.countryList);
    } 
  }
  
  private static void initLocaleMaps() {
    Locale[] arrayOfLocale = Locale.getAvailableLocales();
    int j = arrayOfLocale.length;
    int i = 0;
    while (true) {
      if (i < j) {
        Locale locale = arrayOfLocale[i];
        try {
          String str = locale.getISO3Country();
          if (str.length() > 0)
            iso3CountryToLocaleMap.put(str, locale); 
        } catch (MissingResourceException missingResourceException) {}
        try {
          String str = locale.getISO3Language();
          if (str.length() > 0)
            iso3LanguageToLocaleMap.put(str, locale); 
        } catch (MissingResourceException missingResourceException) {}
        i++;
        continue;
      } 
      return;
    } 
  }
  
  private static Locale iso3CountryToLocale(String paramString) {
    Locale locale3 = iso3CountryToLocaleMap.get(paramString);
    Locale locale2 = locale3;
    if (locale3 == null)
      locale2 = iso3CountryToLocaleMap.get(paramString.toUpperCase(Locale.ENGLISH)); 
    Locale locale1 = locale2;
    if (locale2 == null)
      locale1 = Locale.getDefault(); 
    return locale1;
  }
  
  private static Locale iso3LanguageToLocale(String paramString) {
    Locale locale3 = iso3LanguageToLocaleMap.get(paramString);
    Locale locale2 = locale3;
    if (locale3 == null)
      locale2 = iso3LanguageToLocaleMap.get(paramString.toLowerCase(Locale.ENGLISH)); 
    Locale locale1 = locale2;
    if (locale2 == null)
      locale1 = Locale.getDefault(); 
    return locale1;
  }
  
  @SimpleEvent(description = "Event to raise after the message is spoken. The result will be true if the message is spoken successfully, otherwise it will be false.")
  public void AfterSpeaking(boolean paramBoolean) {
    EventDispatcher.dispatchEvent(this, "AfterSpeaking", new Object[] { Boolean.valueOf(paramBoolean) });
  }
  
  @SimpleProperty(description = "List of the country codes available on this device for use with TextToSpeech.  Check the Android developer documentation under supported languages to find the meanings of these abbreviations.")
  public YailList AvailableCountries() {
    prepareLanguageAndCountryProperties();
    return this.allCountries;
  }
  
  @SimpleProperty(description = "List of the languages available on this device for use with TextToSpeech.  Check the Android developer documentation under supported languages to find the meanings of these abbreviations.")
  public YailList AvailableLanguages() {
    prepareLanguageAndCountryProperties();
    return this.allLanguages;
  }
  
  @SimpleEvent
  public void BeforeSpeaking() {
    EventDispatcher.dispatchEvent(this, "BeforeSpeaking", new Object[0]);
  }
  
  @SimpleProperty
  public String Country() {
    return this.country;
  }
  
  @DesignerProperty(defaultValue = "", editorType = "countries")
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Country code to use for speech generation.  This can affect the pronounciation.  For example, British English (GBR) will sound different from US English (USA).  Not every country code will affect every language.")
  public void Country(String paramString) {
    switch (paramString.length()) {
      default:
        locale = Locale.getDefault();
        this.country = locale.getCountry();
        this.iso2Country = locale.getCountry();
        return;
      case 3:
        locale = iso3CountryToLocale((String)locale);
        this.country = locale.getISO3Country();
        this.iso2Country = locale.getCountry();
        return;
      case 2:
        break;
    } 
    Locale locale = new Locale((String)locale);
    this.country = locale.getCountry();
    this.iso2Country = locale.getCountry();
  }
  
  @SimpleProperty
  public String Language() {
    return this.language;
  }
  
  @DesignerProperty(defaultValue = "", editorType = "languages")
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Sets the language for TextToSpeech. This changes the way that words are pronounced, not the actual language that is spoken.  For example setting the language to and speaking English text with sound like someone speaking English with a Frernch accent.")
  public void Language(String paramString) {
    switch (paramString.length()) {
      default:
        locale = Locale.getDefault();
        this.language = locale.getLanguage();
        this.iso2Language = locale.getLanguage();
        return;
      case 3:
        locale = iso3LanguageToLocale((String)locale);
        this.language = locale.getISO3Language();
        this.iso2Language = locale.getLanguage();
        return;
      case 2:
        break;
    } 
    Locale locale = new Locale((String)locale);
    this.language = locale.getLanguage();
    this.iso2Language = locale.getLanguage();
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns current value of Pitch")
  public float Pitch() {
    return this.pitch;
  }
  
  @DesignerProperty(defaultValue = "1.0", editorType = "float")
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Sets the Pitch for TextToSpeech The values should be between 0 and 2 where lower values lower the tone of synthesized voice and greater values raise it.")
  public void Pitch(float paramFloat) {
    if (paramFloat < 0.0F || paramFloat > 2.0F) {
      Log.i("TextToSpeech", "Pitch value should be between 0 and 2, but user specified: " + paramFloat);
      return;
    } 
    this.pitch = paramFloat;
    ITextToSpeech iTextToSpeech = this.tts;
    float f = paramFloat;
    if (paramFloat == 0.0F)
      f = 0.1F; 
    iTextToSpeech.setPitch(f);
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public boolean Result() {
    return this.result;
  }
  
  @SimpleFunction
  public void Speak(String paramString) {
    BeforeSpeaking();
    Locale locale = new Locale(this.iso2Language, this.iso2Country);
    this.tts.speak(paramString, locale);
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns current value of SpeechRate")
  public float SpeechRate() {
    return this.speechRate;
  }
  
  @DesignerProperty(defaultValue = "1.0", editorType = "float")
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Sets the SpeechRate for TextToSpeech. The values should be between 0 and 2 where lower values slow down the pitch and greater values accelerate it.")
  public void SpeechRate(float paramFloat) {
    if (paramFloat < 0.0F || paramFloat > 2.0F) {
      Log.i("TextToSpeech", "speechRate value should be between 0 and 2, but user specified: " + paramFloat);
      return;
    } 
    this.speechRate = paramFloat;
    ITextToSpeech iTextToSpeech = this.tts;
    float f = paramFloat;
    if (paramFloat == 0.0F)
      f = 0.1F; 
    iTextToSpeech.setSpeechRate(f);
  }
  
  public void onDestroy() {
    this.tts.onDestroy();
  }
  
  public void onResume() {
    this.tts.onResume();
  }
  
  public void onStop() {
    this.tts.onStop();
  }
  
  public void prepareLanguageAndCountryProperties() {
    if (!this.isTtsPrepared) {
      if (!this.tts.isInitialized()) {
        this.form.dispatchErrorOccurredEvent(this, "TextToSpeech", 2701, new Object[0]);
        Speak("");
        return;
      } 
    } else {
      return;
    } 
    getLanguageAndCountryLists();
    this.isTtsPrepared = true;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/TextToSpeech.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */