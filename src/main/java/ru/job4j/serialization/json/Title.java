package ru.job4j.serialization.json;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

    @XmlRootElement(name = "title")
    public class Title {

        public Title() {
        }

        @XmlAttribute
        private String title;

        public Title(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

         @Override
         public String toString() {
           return "Title{"
               + "title='" + title + '\''
               + '}';
         }
    }
