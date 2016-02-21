<?xml version="1.0" encoding="UTF-8"?>

<!-- Created by Clement on 090524 -->

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:mb="http://musicbrainz.org/ns/mmd-1.0#">
<xsl:template match="/">
<RESULT>
    <xsl:for-each select="*[local-name()='metadata']/*[local-name()='recording-list']/*[local-name()='recording']">
        		 <xsl:text>&#10;</xsl:text> <RECORD>
                     <xsl:text>&#10; &#32;</xsl:text>  <ITEM ANGIE-VAR='?songTitle'><xsl:value-of select="mb:title"/></ITEM>
                     <xsl:text>&#10; &#32;</xsl:text>  <ITEM ANGIE-VAR='?artistId'><xsl:value-of select="mb:artist-credit/name-credit/artist@id"/></ITEM>
                     <xsl:text>&#10; &#32;</xsl:text>  <ITEM ANGIE-VAR='?albumId'><xsl:value-of select="mb:release-list/release@id"/></ITEM>
                                           
                     
                 
        		  </RECORD> 
                </xsl:when>
                
<!-- 
                <xsl:when test="@type = 'Group'">
                    
                </xsl:when>
 -->
 
    </xsl:for-each>  
</RESULT>
</xsl:template>
</xsl:stylesheet>